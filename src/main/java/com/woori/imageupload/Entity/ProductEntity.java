package com.woori.imageupload.Entity;

import jakarta.persistence.*;
import lombok.*;

//상품테이블
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity

@Table(name = "product")//테이블명
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;     //일련번호
    @Column(name = "product", length = 100)
    private String product;  //상품명
    @Column(name = "detail", length = 200)
    private String detail;   //상세정보
    @Column(name = "quantity")
    private Integer quantity;//재고수량
    @Column(name = "price")
    private Integer price;   //가격
    @Column(name = "sale")
    private Integer sale;    //세일상픔
    @Column(name = "state")
    private Integer state;   //상품상품
    @Column(name = "img")
    private String img;      //이미지 파일명
}
