package tradatorii.gym_management.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tradatorii.gym_management.Entity.User;
import tradatorii.gym_management.Service.implementations.UserService;
import tradatorii.gym_management.minio.MinioService;

@RequestMapping("/minio")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8020", // Replace with your frontend's URL
        allowedHeaders = {"Authorization", "Content-Type"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
        allowCredentials = "true")
public class MinioController {

    private final MinioService minioService;
    private final UserService userService;

    @PostMapping("/createBucket")
    public void createBucket(@RequestParam String bucketName) {
        minioService.createBucket(bucketName);
    }

    @GetMapping("/generate-url")
    public String generatePreSignedUrl(@AuthenticationPrincipal User user) throws Exception {
        String bucketName = user.getUserBucket();
        String objectName = user.getProfilePhotoObjectName();
        if(objectName.equals("defaultProfilePhoto.png"))
            return minioService.generatePreSignedUrl("default-values", "defaultProfilePhoto.png");
        return minioService.generatePreSignedUrl(bucketName, objectName);
    }

    @GetMapping("/generate-manager-url")
    public String generateManagerPreSignedUrl(@RequestParam Long userId){
        User user = userService.getById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return userService.generatePreSignedUrl(user);
    }
}
