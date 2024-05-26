package ee.spacexy.whatwhere.service.service.firebase.impl;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.firebase.cloud.FirestoreClient;
import ee.spacexy.whatwhere.service.service.CategoryService;
import ee.spacexy.whatwhere.service.service.LayerService;
import ee.spacexy.whatwhere.service.service.dto.FirebaseCategoryDTO;
import ee.spacexy.whatwhere.service.service.dto.FirebaseLayerDTO;
import ee.spacexy.whatwhere.service.service.dto.LayerDTO;
import ee.spacexy.whatwhere.service.service.firebase.FirebaseService;
import ee.spacexy.whatwhere.service.service.firebase.mapper.FirebaseCategoryMapper;
import ee.spacexy.whatwhere.service.service.firebase.mapper.FirebaseLayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirebaseServiceImpl implements FirebaseService {

    private final LayerService layerService;

    private final CategoryService categoryService;

    private final FirebaseCategoryMapper firebaseCategoryMapper;

    private final FirebaseLayerMapper firebaseLayerMapper;


    @Override
    public List<LayerDTO> updateUserLayers(String userId) {
        Firestore db = FirestoreClient.getFirestore();
        List<LayerDTO> layers = layerService.getLayers();
        List<FirebaseCategoryDTO> categories = firebaseCategoryMapper.toFirebaseModel(categoryService.getCategories());
        WriteBatch batch = db.batch();

        DocumentReference userRef = db.collection("users").document(userId);
        CollectionReference categoriesCol = userRef.collection("categories");

        for (FirebaseCategoryDTO category : categories) {
            String customCategoryId = category.getTitle();
            DocumentReference categoryDoc = categoriesCol.document(customCategoryId);
            category.setChecked(false);
            category.setLayers(firebaseLayerMapper.toFirebaseModel(layerService.getLayersByCategoryId(category.getId())));
            batch.set(categoryDoc, category);
        }

        batch.commit();
//
//        for (FirebaseCategoryDTO category : categories) {
//            updateCategoryLayers(category, categoriesCol);
//        }

        return layers;
    }

    private void updateCategoryLayers(FirebaseCategoryDTO category, CollectionReference categoriesCol) {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();
        List<FirebaseLayerDTO> layers = firebaseLayerMapper.toFirebaseModel(layerService.getLayersByCategoryId(category.getId()));

        DocumentReference categoryRef = categoriesCol.document(category.getTitle());
        CollectionReference layersCol = categoryRef.collection("layers");

        for (FirebaseLayerDTO layer : layers) {
            String customLayerId = layer.getSchema() + ":" + layer.getName();
            DocumentReference layerDoc = layersCol.document(customLayerId);
            layer.setVisible(false);
            batch.set(layerDoc, layer);
        }

        batch.commit();
    }
}
