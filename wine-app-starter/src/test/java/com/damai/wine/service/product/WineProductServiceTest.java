package com.damai.wine.service.product;

import com.damai.wine.api.common.enums.ChineseZodiacEnum;
import com.damai.wine.api.common.enums.PackageEnum;
import com.damai.wine.api.common.enums.WineBrandEnum;
import com.damai.wine.api.common.enums.WineTypeEnum;
import com.damai.wine.api.service.request.product.WineProductAddRequest;
import com.damai.wine.service.TestSupport;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WineProductServiceTest extends TestSupport {

    @Autowired
    WineProductService wineProductService;

    @Test
    public void insertWineProductTest(){
        WineProductAddRequest wineProductAddRequest = new WineProductAddRequest();
        wineProductAddRequest.setWineType(WineTypeEnum.MAO_TAI.getValue());
        wineProductAddRequest.setWineBrand(WineBrandEnum.FEI_TIAN.getValue());
        wineProductAddRequest.setProductionYear("2021");
        wineProductAddRequest.setDegree("53");
        wineProductAddRequest.setCapacity("500");
        if (wineProductAddRequest.getWineBrand().equals(WineBrandEnum.SHENG_XIAO.getValue())){
            wineProductAddRequest.setChineseZodiac(ChineseZodiacEnum.CATTLE.getValue());
        }
        wineProductAddRequest.setPackaging(PackageEnum.SINGLE.getValue());
        wineProductAddRequest.setBottleNumber("1");

        String id = wineProductService.insertWineProduct(wineProductAddRequest);
        System.out.println("id:" + id);
    }



}
