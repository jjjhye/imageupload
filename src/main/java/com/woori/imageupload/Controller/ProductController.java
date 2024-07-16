package com.woori.imageupload.Controller;

import com.woori.imageupload.DTO.ProductDTO;
import com.woori.imageupload.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//1.HTML연결(디자인)
//2.Service로 작업지시
@Controller
@RequiredArgsConstructor
@Tag(name = "상품관리", description = "상품과 이미지를 처리")//swagger-클래스이름과 설명
public class ProductController {
    private final ProductService productService;

    //7개
    //삽입
    @Operation(summary="상품등록품", description = "상품등록폼으로 이동")//swagger 맵핑명과 설명
    @GetMapping("/product/insert")
    public String insertView(){
        return "product/insert";
    }
    @Operation(summary="상품등록처리", description = "상품정보를 데이터베이스에 저장")
    @PostMapping("/product/insert")
    //<input type="file" class="form-control" name="imgFile">
    public String insertProc(ProductDTO productDTO, MultipartFile imgFile){
        productService.insert(productDTO, imgFile);
        return "redirect:/product/list";//목록으로 이동
    }
    //수정
    @Operation(summary="상품수정품", description = "상품수정폼으로 이동")
    @GetMapping("/product/update")
    public String updateView(Integer pid, Model model){
        ProductDTO productDTO = productService.read(pid);
        model.addAttribute("data", productDTO);
        return "product/update";
    }
    @Operation(summary ="상품수정처리", description = "상품정보를 데이터베이스에서 수정")
    @PostMapping("/product/update")
    public String updateProc(ProductDTO productDTO, MultipartFile imgFile){
        productService.update(productDTO, imgFile);
        return "redirect:/product/list";//목록으로 이동
    }
    //삭제
    @Operation(summary="상품삭제처리", description = "상품정보를 데이터베이스에서 삭제")
    @GetMapping("/product/delete")
    public String deleteProc(Integer pid){
        productService.delete(pid);
        return "redirect:/product/list";//목록으로 이동
    }
    //상세보기
    @Operation(summary="상품상세보기", description = "상품정보를 데이터베이스에서 읽어서 HTML에 상세출력")
    @GetMapping("/product/detail")
    public String readView(Integer pid, Model model){
        ProductDTO productDTO = productService.read(pid);
        model.addAttribute("data", productDTO);
        return "product/detail";
    }
    //목록
    @Operation(summary="상품목록보기", description = "모든 상품정보를 데이터베이스에서 읽어서 HTML에출력")
    @GetMapping({"/","/product/list"})
    public String listView(Model model){
        List<ProductDTO> productDTOS = productService.list();
        model.addAttribute("list", productDTOS);
        return "product/list";
    }

}
