package dev.abidino.secondround.ballotbox.business;

import dev.abidino.secondround.ballotbox.data.BulletBoxDataService;
import dev.abidino.secondround.ballotbox.data.BulletBoxEntity;
import dev.abidino.secondround.ballotbox.web.BulletBoxDto;
import dev.abidino.secondround.exception.BadRequestException;
import dev.abidino.secondround.exception.ErrorMessageType;
import dev.abidino.secondround.region.city.business.City;
import dev.abidino.secondround.region.city.business.CityService;
import dev.abidino.secondround.region.city.data.CityEntity;
import dev.abidino.secondround.region.district.business.District;
import dev.abidino.secondround.region.district.business.DistrictService;
import dev.abidino.secondround.region.district.data.DistrictEntity;
import dev.abidino.secondround.security.ApiJWTAuthorizationFilter;
import dev.abidino.secondround.user.business.Role;
import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.business.UserService;
import dev.abidino.secondround.user.data.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record BulletBoxServiceImpl(BulletBoxDataService bulletBoxDataService,
                                   UserService userService,
                                   DistrictService districtService,
                                   CityService cityService) implements BulletBoxService {
    @Override
    public BulletBox save(BulletBox bulletBox) {
        BulletBoxEntity bulletBoxEntity = new BulletBoxEntity(bulletBox);
        BulletBoxEntity boxEntity = bulletBoxDataService.save(bulletBoxEntity);
        return new BulletBox(boxEntity);
    }

    @Override
    public List<BulletBox> findAll() {
        return bulletBoxDataService.findAll().stream().map(BulletBox::new).toList();
    }

    @Override
    public BulletBox findByAttendant(String username) {
        User user = userService.findByUsername(username);
        return findByAttendant(user);
    }

    @Override
    public BulletBox update(BulletBox bulletBox, Long id) {
        findById(id);
        return save(new BulletBox(bulletBox, id));
    }

    @Override
    public BulletBox findById(Long id) {
        return new BulletBox(bulletBoxDataService.findById(id));
    }

    @Override
    public BulletBox convertDto(BulletBoxDto bulletBoxDto) {
        User user = null;
        District district = null;
        if (Objects.nonNull(bulletBoxDto.attendantName())) {
            user = userService.findByUsername(bulletBoxDto.attendantName());
        }
        if (Objects.nonNull(bulletBoxDto.districtId())) {
            district = districtService.findById(bulletBoxDto.districtId());
        }
        return new BulletBox(bulletBoxDto, user, district);
    }

    @Override
    public BulletBox updateCount(Long id, long kkCount, long rteCount, long invalidCount) {
        BulletBox bulletBox = findById(id);
        isAuthenticatedUser(bulletBox);
        bulletBox.setCounts(kkCount, rteCount, invalidCount);
        return update(bulletBox, id);
    }

    private void isAuthenticatedUser(BulletBox bulletBox) {
        String authenticatedUserName = ApiJWTAuthorizationFilter.getAuthenticatedUserName();
        User user = userService.findByUsername(authenticatedUserName);
        if (user.getRole() == Role.ADMIN || Objects.equals(bulletBox.getAttendant(), user)) {
            return;
        }
        throw new BadRequestException(ErrorMessageType.FORBIDDEN.getMessage());
    }

    private BulletBox findByAttendant(User user) {
        UserEntity userEntity = new UserEntity(user);
        BulletBoxEntity bulletBoxEntity = bulletBoxDataService.findByUserEntity(userEntity);
        return new BulletBox(bulletBoxEntity);
    }

    @Override
    public List<BulletBox> getAllVoteCount() {
        return bulletBoxDataService.findAll().stream().map(BulletBox::new).toList();
    }

    @Override
    public List<BulletBox> getAllVoteCountByFilter(Long cityId, Long districtId) {
        if (Objects.nonNull(districtId)) {
            District district = districtService.findById(districtId);
            DistrictEntity districtEntity = new DistrictEntity(district);
            return bulletBoxDataService.findByDistrictId(districtEntity).stream().map(BulletBox::new).toList();
        } else if (Objects.nonNull(cityId)) {
            City city = cityService.findById(cityId);
            return bulletBoxDataService.findByCityId(new CityEntity(city)).stream().map(BulletBox::new).toList();
        } else {
            return getAllVoteCount();
        }
    }
}
