<template>
  <div class="productItems">
    <!-- 반복문으로 더미데이터 보여주기 -->
    <div
      v-for="item in productItems"
      :key="item.id"
      class="card"
      style="width: 18rem"
    >
      <img
        :src="item.productImg"
        class="card-img-top"
        :alt="item.productName"
      />
      <div class="card-body">
        <h5 class="card-title">{{ item.productName }}</h5>
        <div class="card-text">
          <div>{{ item.productPrice }}</div>
          <div>{{ item.productDetail }}</div>
        </div>
        <a href="#" class="btn btn-primary">상세 보기</a>
      </div>
    </div>
    <!-- 반복문으로 더미데이터 보여주기 -->
  </div>
</template>
<script>
export default {
  name: "ojtProduct",
  components: {},
  //더미데이터
  data() {
    return {
      productItems: [
        {
          id: 1,
          productName: "엑스캅터",
          productImg:
            "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcRL-JEF5CkYKUCN88ZU2uL9vVojImxzHBAuvL1vP01OKe-RdhjOHBfi7pCL32H5-15NPOhXpovDDo9k_ByJR_tmivZY2-h7LPgAka9wQOD2ceVpyELDkh-ARA&usqp=CAE",
          productPrice: "200,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 2,
          productName: "젠뮤즈",
          productImg:
            "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTPCtzZZv2LIr1QXzTw64NMUr7VQT6Bn1nC48-V7N2KZIvYDDRz4RPiK1FBQtnwdhwvf6I3FmnxlsZqDQDTJS00wN-nh2_bx7zdiYLgpQ5e&usqp=CAE",
          productPrice: "300,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 3,
          productName: "조명있는 전문가용 드론",
          productImg:
            "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcSHKF45Q4bAqVXIK_r27V45FXKWRK31TUqyxyjocV2YIWJ8SGfuhzMS0GWhF6PccshvuqjQVcQz_AniHI-0qKtL1WYX3kBvGoRKRP8HkwqHNmHx0yrJ-m5CuA&usqp=CAE",
          productPrice: "100,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 4,
          productName: "입문용 드론",
          productImg:
            "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcSgneOp5mlDtFzo5abYGPNffVJtZP_03yZ1adQpxbRJ-7WX00Dpsz0hFkDjzfq3p1Ck8LtsbvkgPUcrAuEohG_QhhpyGY3XWV1xxrhMtxCh&usqp=CAE",
          productPrice: "64,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 5,
          productName: "궤도탱크 2in1",
          productImg:
            "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcQ14EAnSfnvBKYYNGU4BjBeobBl3p6MwdPeC8bcSvsJBH-vDlMjfteuzZNURa_k3c-vpyLlOvZXukQcNCIC9cGvUrrIrqtNoFFu_FtH3hr2pQgjjjTa7KWuMQ&usqp=CAE",
          productPrice: "85,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 6,
          productName: "DJI 팬텀",
          productImg:
            "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTYxEwbzqI2s9XH5R5Gzy-nYsdwB5IqqjNM6GuL9fQ1I7QLvM85GG-GCHLos9E81r_hAxfozU0QXviRUEuyA6or9677CRZfU_H4YVR3kx0uVIM-k_aREYhcnQ&usqp=CAE",
          productPrice: "500,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 7,
          productName: "DJI MINI 4 PRO",
          productImg:
            "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcQl7uSLhc1kIMqm6wFsYjTGDQvpx6NQpxEc-8G19OtGI9ZhI4Y2U29g2PWYPvFmNHI_jSoDBT2F_bgpIFcjeFqfSC3hxCRRdSScSiz3tyE&usqp=CAE",
          productPrice: "1,356,400원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 8,
          productName: "초경량 미니 DJI 드론",
          productImg:
            "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRMOZz0CSDo0S_I5112Paj_wGy-GpLKnKxMdTSBFpAfVaAO0RTl6mgXwIxovVKjykgbup6eevG_T0dHZDdw2FEoEZt7MhAJYQ&usqp=CAE",
          productPrice: "276,300원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 9,
          productName: "플라잉 씽 드론",
          productImg:
            "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTSgFr8p2HivlKIqaNuU-Vmyd4fZ4rRkDoAjuJ8npf6iFoV9phMN_FgSXCk6Vpy40lwXluTWI25LAdlvYqhFaVkmiHAdVZG&usqp=CAE",
          productPrice: "46,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 11,
          productName: "DJI MINI 3 RRO",
          productImg:
            "https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcTtQ7gpRLHVxN4HoPR3OwXK6ZLKHdBqyTTtYmZpcDj3G-OC_vNrkd6ie_hGJW86HJoCtAtr45SKGR-ZqkeMX_CHatLYVM4K1e-GRJQPnFAQMEWeUQy7N_Jc9Q&usqp=CAE",
          productPrice: "900,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 12,
          productName: "JJRC 슈퍼소닉",
          productImg:
            "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcTGqXG5bITudteLpLUyMSXmtMKOV6I-ioG5THJlqPcSXKqdLdMa-I02WsnNhnfzv8ngHbjG2EliyV07MLwMGvF8MlPYirYrkrCe-PXux84kR7KGmGZEFSsV&usqp=CAE",
          productPrice: "40,000원",
          productDetail: "배송비 3,000원",
        },
        {
          id: 13,
          productName: "DJI 매빅3 멀티스펙트럴",
          productImg:
            "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcQBSexktavVXMuvVbgdYYiWhteAcwe-DNvWMa260XW4r43VDehPr_dSGWE55NZxQ4pLp_OVJ_ugollkke5gQL-7YsiGz520MVZU_l8VIkmWTtKE0LK-pLnS&usqp=CAE",
          productPrice: "7,194,000원",
          productDetail: "배송비 3,000원",
        },
      ],
    };
  },
};
</script>
<style>
.productItems {
  padding: 24px;
  display: grid;
  justify-items: center;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  grid-auto-rows: minmax(100px, auto);
  gap: 20px;
}
.card {
}
</style>
