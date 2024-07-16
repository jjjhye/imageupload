package com.woori.imageupload.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

//사용자가 임의로 작성한 메소드
//이미지파일을 받아서 c:/product/image에 저장
//a.jpg->a.jpg, a.jpg-> 기존 삭제 ->a.jpg(덮어쓰기)
//파일명은 중복x ->a.jpg->123456788.jpg(난수로 이름을 생성해서 저장)-UUID(중복이 불가능한 이름을 생성)
@Component
public class FileUpload {
    @Value("${imgLocation}")
    private String imgLocation;//c:/product/image/에 이미지 저장위치

    //a.jpg
    //a->32423423
    //.jpg => 32423423.jpg
    //c:/product/image/32423423.jpg 저잗

    //OriginalFileName : 파일명, imageFile : 실질적인 파일
    public String uploadFile(MultipartFile imageFile) {
        String OriginalFileName = imageFile.getOriginalFilename();

        UUID uuid = UUID.randomUUID();//임의(난수)로 이름을 생성,파일명
        //a.jpg(파일명)에서 뒤에서 .까지 문자열 추출 => jpg(파일의 확장자를 추출)
        String extendsion = OriginalFileName.substring(OriginalFileName.lastIndexOf("."));
        //저장할 파일명 난수+확장자(2456433.jpg)=>새로운 파일명 지정
        String saveFileName = uuid.toString() + extendsion;
        //c:/product/image/2456433.jpg
        String uploadFullUrl = imgLocation + saveFileName;//저장할 위치롸 파일명(작업)

        //이미지를 하드디스크에 저장하는 작업
        File folder = new File(imgLocation);
        if(!folder.exists()){//만약에 c:/product/image 폴더가 folder.exists(존재하면) !하지 않으면
            boolean result = folder.mkdirs();//c:/product/image폴더를 생성한다.
        }
        //외부작업, 장비, 장치 등 작업시 try를 사용해서 예외처리를 설정
        try{//작업을 시도해서
            byte[] filedata = imageFile.getBytes();//이미지를 한글자(1Byte)씩 읽어온다
            FileOutputStream fos = new FileOutputStream(uploadFullUrl);//파일에 연속적으로 쓰기
            fos.write(filedata);//지정된 파일에 쓰기(저장)
            fos.close();//파일닫기

            return saveFileName;
        }catch (Exception e){//오류가 발생하면
            return null;

        }
    }
    public void deleteFile(String fileName) {//a124565.jpg
        String deleteFileName = imgLocation+fileName;//c:/product/image/a124565.jpg 위치

        File deleteFile = new File(deleteFileName);
        if(deleteFile.exists()){//파일이 존재하면
            deleteFile.delete();//c:/product/image/a124565.jpg 삭제
        }
    }
}
