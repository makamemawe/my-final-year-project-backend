import com.mawe.kizimbani.entity.ProductionMember;
import com.mawe.kizimbani.entity.ProductionSite;
import com.mawe.kizimbani.service.ProductionMemberService;
import com.mawe.kizimbani.service.ProductionSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import java.util.NoSuchElementException;

@RestController
//@RequestMapping("/api/production-members")
public class ProductionMemberController {

    @Autowired
    private ProductionMemberService productionMemberService;

    @Autowired
    private ProductionSiteService productionSiteService;  // Assume this service is available

    @PostMapping({"/addNewMember"})
    public ResponseEntity<ProductionMember> addNewMember(@RequestBody ProductionMember productionMember) {
        ProductionMember savedMember = productionMemberService.saveProductionMember(productionMember);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }
//    @PostMapping({"/addNewMember"})
//    public ResponseEntity<ProductionMember> createProductionMember(
//            @RequestParam("fullName") String fullName,
//            @RequestParam("email") String email,
//            @RequestParam("phone") String phone,
//            @RequestParam("fieldLevel") String fieldLevel,
//            @RequestParam("registrationNumber") String registrationNumber,
//            @RequestParam("profileImage") MultipartFile profileImage,
//            @RequestParam("siteId") Long siteId
//    ) throws IOException {
//        ProductionMember member = new ProductionMember();
//        member.setFullName(fullName);
//        member.setEmail(email);
//        member.setPhone(phone);
//        member.setFieldLevel(fieldLevel);
//        member.setRegistrationNumber(registrationNumber);
//        member.setProfileImage(profileImage.getBytes());
//
//        ProductionSite site = productionSiteService.getSiteById(siteId)
//                .orElseThrow(() -> new NoSuchElementException("ProductionSite not found with id: " + siteId));
//        member.setProductionSite(site);
//
//        ProductionMember savedMember = productionMemberService.saveProductionMember(member);
//        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
//    }

    @GetMapping({"/getAllMembers"})
    public ResponseEntity<List<ProductionMember>> getAllProductionMembers() {
        List<ProductionMember> members = productionMemberService.getAllProductionMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping({"/getMemberById/{id}"})
    public ResponseEntity<ProductionMember> getProductionMemberById(@PathVariable Long id) {
        Optional<ProductionMember> member = productionMemberService.getProductionMemberById(id);
        return member.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProductionMember> updateProductionMember(
//            @PathVariable Long id,
//            @RequestParam("fullName") String fullName,
//            @RequestParam("email") String email,
//            @RequestParam("phone") String phone,
//            @RequestParam("fieldLevel") String fieldLevel,
//            @RequestParam("registrationNumber") String registrationNumber,
//            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
//            @RequestParam("siteId") Long siteId
//    ) throws IOException {
//        ProductionMember member = new ProductionMember();
//        member.setFullName(fullName);
//        member.setEmail(email);
//        member.setPhone(phone);
//        member.setFieldLevel(fieldLevel);
//        member.setRegistrationNumber(registrationNumber);
//        if (profileImage != null && !profileImage.isEmpty()) {
//            member.setProfileImage(profileImage.getBytes());
//        }
//        member.setProductionSite(productionSiteService.getSiteById(siteId).orElseThrow());
//
//        ProductionMember updatedMember = productionMemberService.updateProductionMember(id, member);
//        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
//    }

    @DeleteMapping("/deleteMember/{id}")
    public ResponseEntity<Void> deleteProductionMember(@PathVariable Long id) {
        productionMemberService.deleteProductionMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

