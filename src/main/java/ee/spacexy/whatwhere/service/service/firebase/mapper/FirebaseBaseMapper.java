package ee.spacexy.whatwhere.service.service.firebase.mapper;

import java.util.List;

public interface FirebaseBaseMapper<DTO, FIREBASE_MODEL> {

    FIREBASE_MODEL toFirebaseModel(DTO m);

    List<FIREBASE_MODEL> toFirebaseModel(List<DTO> m);

}
