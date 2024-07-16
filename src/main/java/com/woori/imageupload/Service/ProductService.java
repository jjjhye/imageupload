package com.woori.imageupload.Service;

import com.woori.imageupload.DTO.ProductDTO;
import com.woori.imageupload.Entity.ProductEntity;
import com.woori.imageupload.Repository.ProductRepository;
import com.woori.imageupload.Util.FileUpload;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional //일괄처리, 데이터베이스 속도 향상
public class ProductService {
    //필요한 클래스
    private final ProductRepository productRepository;//데이테베이스 작업
    private final ModelMapper modelMapper;//DTO와 Entity간에 변환
    private final FileUpload fileUpload;//이미지파일 저장 및 삭제

    //삽입
    //이미지파일->폴더에 저장->새로 바뀐이름을 데이터베이스 저장
    public void insert(ProductDTO productDTO, MultipartFile imgFile){//상품정보, 이미지파일
        //데이터베이스 작업전에 이미지를 먼저 저장(추가)
        String newFileName = fileUpload.uploadFile(imgFile);//이미지파일 저장 후 새로은 이름을 받는다.
        productDTO.setImg(newFileName);//새로운 파일명을 저장

        //이전작업동일
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        productRepository.save(productEntity);
    }
    //수정
    public void update(ProductDTO productDTO, MultipartFile imgFile){
        //수정할 파일이 존재하는지 확인
        ProductEntity productEntity = productRepository.findById(productDTO.getPid())
                .orElseThrow();//오류 발생시 수정작업을 중단
        //기존에 저장된 이미지 파일을 삭제
        String deleteFile = productEntity.getImg();//이미지파일을 읽어와서
        fileUpload.deleteFile(deleteFile);//기존이미지파일을 삭제한다

        String newFileName = fileUpload.uploadFile(imgFile);//새로 바뀐 이미지를 저장
        productDTO.setImg(newFileName);

        ProductEntity data = modelMapper.map(productDTO, ProductEntity.class);//수정된 내용을 변환
        productRepository.save(data);//수정
    }
    //삭제
    public void delete(Integer pid){
        //관련이미지를 삭제(파일명을 찾기위해 조회)
        ProductEntity productEntity = productRepository.findById(pid).orElseThrow();
        fileUpload.deleteFile(productEntity.getImg());

        //이전작업
        productRepository.deleteById(pid);
    }
    //개별조회
    public ProductDTO read(Integer pid){
        ProductEntity productEntity = productRepository.findById(pid).orElseThrow();

        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
        return productDTO;
    }
    //전체조회
    public List<ProductDTO>list(){
        List<ProductEntity>productEntities = productRepository.findAll();
        List<ProductDTO> productDTOS = Arrays.asList(
                modelMapper.map(productEntities, ProductDTO[].class)
        );
        return productDTOS;
    }

}
