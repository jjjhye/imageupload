package com.woori.imageupload.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductDTO {
    private Integer pid;     //일련번호
    private String product;  //상품명
    private String detail;   //상세정보
    private Integer quantity;//재고수량
    private Integer price;   //가격
    private Integer sale;    //세일상픔
    private Integer state;   //상품상품
    private String img;      //이미지 파일명
    private LocalDateTime modDate; //날짜
}
