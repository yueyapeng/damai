package com.damai.wine.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_wine_product")
public class WineProduct {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 酒水类型(茅台、五粮液)
     */
    @Column(name = "wine_type")
    private String wineType;

    /**
     * 酒水系列(飞天、生肖系列)
     */
    @Column(name = "wine_brand")
    private String wineBrand;

    /**
     * 生产年份
     */
    @Column(name = "production_year")
    private String productionYear;

    /**
     * 酒水度数
     */
    private String degree;

    /**
     * 酒容量（500ml、1000ml）
     */
    private String capacity;

    /**
     * 生肖
     */
    @Column(name = "chinese_zodiac")
    private String chineseZodiac;

    /**
     * 包装(1:散装、2:原箱)
     */
    private String packaging;

    /**
     * 如果packaging=2 原箱包装，每箱的瓶数
     */
    @Column(name = "bottle_number")
    private String bottleNumber;

    /**
     * 当前产品状态（Y：上架、N：已下架）
     */
    private String status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取酒水类型(茅台、五粮液)
     *
     * @return wine_type - 酒水类型(茅台、五粮液)
     */
    public String getWineType() {
        return wineType;
    }

    /**
     * 设置酒水类型(茅台、五粮液)
     *
     * @param wineType 酒水类型(茅台、五粮液)
     */
    public void setWineType(String wineType) {
        this.wineType = wineType;
    }

    /**
     * 获取酒水系列(飞天、生肖系列)
     *
     * @return wine_brand - 酒水系列(飞天、生肖系列)
     */
    public String getWineBrand() {
        return wineBrand;
    }

    /**
     * 设置酒水系列(飞天、生肖系列)
     *
     * @param wineBrand 酒水系列(飞天、生肖系列)
     */
    public void setWineBrand(String wineBrand) {
        this.wineBrand = wineBrand;
    }

    /**
     * 获取生产年份
     *
     * @return production_year - 生产年份
     */
    public String getProductionYear() {
        return productionYear;
    }

    /**
     * 设置生产年份
     *
     * @param productionYear 生产年份
     */
    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    /**
     * 获取酒水度数
     *
     * @return degree - 酒水度数
     */
    public String getDegree() {
        return degree;
    }

    /**
     * 设置酒水度数
     *
     * @param degree 酒水度数
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * 获取酒容量（500ml、1000ml）
     *
     * @return capacity - 酒容量（500ml、1000ml）
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * 设置酒容量（500ml、1000ml）
     *
     * @param capacity 酒容量（500ml、1000ml）
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    /**
     * 获取生肖
     *
     * @return chinese_zodiac - 生肖
     */
    public String getChineseZodiac() {
        return chineseZodiac;
    }

    /**
     * 设置生肖
     *
     * @param chineseZodiac 生肖
     */
    public void setChineseZodiac(String chineseZodiac) {
        this.chineseZodiac = chineseZodiac;
    }

    /**
     * 获取包装(1:散装、2:原箱)
     *
     * @return packaging - 包装(1:散装、2:原箱)
     */
    public String getPackaging() {
        return packaging;
    }

    /**
     * 设置包装(1:散装、2:原箱)
     *
     * @param packaging 包装(1:散装、2:原箱)
     */
    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    /**
     * 获取如果packaging=2 原箱包装，每箱的瓶数
     *
     * @return bottle_number - 如果packaging=2 原箱包装，每箱的瓶数
     */
    public String getBottleNumber() {
        return bottleNumber;
    }

    /**
     * 设置如果packaging=2 原箱包装，每箱的瓶数
     *
     * @param bottleNumber 如果packaging=2 原箱包装，每箱的瓶数
     */
    public void setBottleNumber(String bottleNumber) {
        this.bottleNumber = bottleNumber;
    }

    /**
     * 获取当前产品状态（Y：上架、N：已下架）
     *
     * @return status - 当前产品状态（Y：上架、N：已下架）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置当前产品状态（Y：上架、N：已下架）
     *
     * @param status 当前产品状态（Y：上架、N：已下架）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}