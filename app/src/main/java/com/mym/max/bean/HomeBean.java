package com.mym.max.bean;


import com.mym.max.base.BaseBean;

import java.math.BigDecimal;
import java.util.List;

public class HomeBean extends BaseBean {


    /**
     * data : {"salesActivities":[{"id":3,"name":"多买","bannerImg":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/201705091022222017-05-09_101952.png"},{"id":2,"name":"满500减100","bannerImg":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/201705091020162017-05-09_101952.png"},{"id":1,"name":"全场八折","bannerImg":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/20170509101714无标题.png"}],"typeList":[{"id":1,"name":"新鲜水果","doubSort":1},{"id":2,"name":"时令蔬菜","doubSort":2},{"id":3,"name":"水产海鲜","doubSort":3},{"id":4,"name":"鲜肉制品","doubSort":4}],"skuList":[{"id":6,"name":"小苹果","price":2323,"img":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/20170508160007无标题.png","curWeight":"2g","maxCount":19,"salesName":"全场八折"},{"id":5,"name":"大苹果","price":458,"img":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/20170508160007无标题.png","curWeight":"2g","maxCount":3,"salesName":"全场八折"}],"skuTotalCount":7}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * salesActivities : [{"id":3,"name":"多买","bannerImg":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/201705091022222017-05-09_101952.png"},{"id":2,"name":"满500减100","bannerImg":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/201705091020162017-05-09_101952.png"},{"id":1,"name":"全场八折","bannerImg":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/20170509101714无标题.png"}]
         * typeList : [{"id":1,"name":"新鲜水果","doubSort":1},{"id":2,"name":"时令蔬菜","doubSort":2},{"id":3,"name":"水产海鲜","doubSort":3},{"id":4,"name":"鲜肉制品","doubSort":4}]
         * skuList : [{"id":6,"name":"小苹果","price":2323,"img":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/20170508160007无标题.png","curWeight":"2g","maxCount":19,"salesName":"全场八折"},{"id":5,"name":"大苹果","price":458,"img":"http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/20170508160007无标题.png","curWeight":"2g","maxCount":3,"salesName":"全场八折"}]
         * skuTotalCount : 7
         */

        private int skuTotalCount;
        private List<SalesActivitiesBean> salesActivities;
        private List<TypeListBean> typeList;
        private List<SkuListBean> skuList;

        public int getSkuTotalCount() {
            return skuTotalCount;
        }

        public void setSkuTotalCount(int skuTotalCount) {
            this.skuTotalCount = skuTotalCount;
        }

        public List<SalesActivitiesBean> getSalesActivities() {
            return salesActivities;
        }

        public void setSalesActivities(List<SalesActivitiesBean> salesActivities) {
            this.salesActivities = salesActivities;
        }

        public List<TypeListBean> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<TypeListBean> typeList) {
            this.typeList = typeList;
        }

        public List<SkuListBean> getSkuList() {
            return skuList;
        }

        public void setSkuList(List<SkuListBean> skuList) {
            this.skuList = skuList;
        }

        public static class SalesActivitiesBean {
            /**
             * id : 3
             * name : 多买
             * bannerImg : http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/201705091022222017-05-09_101952.png
             */

            private int id;
            private String name;
            private String bannerImg;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBannerImg() {
                return bannerImg;
            }

            public void setBannerImg(String bannerImg) {
                this.bannerImg = bannerImg;
            }
        }

        public static class TypeListBean {
            /**
             * id : 1
             * name : 新鲜水果
             * doubSort : 1
             */

            private int id;
            private String name;
            private int doubSort;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getDoubSort() {
                return doubSort;
            }

            public void setDoubSort(int doubSort) {
                this.doubSort = doubSort;
            }
        }

        public static class SkuListBean {
            /**
             * id : 6
             * name : 小苹果
             * price : 2323
             * img : http://ada-easylife-text-image.oss-cn-beijing.aliyuncs.com/20170508160007无标题.png
             * curWeight : 2g
             * maxCount : 19
             * salesName : 全场八折
             */

            private int id;
            private String name;
            private BigDecimal price;
            private String img;
            private String curWeight;
            private int maxCount;
            private String salesName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public BigDecimal getPrice() {
                return price;
            }

            public void setPrice(BigDecimal price) {
                this.price = price;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getCurWeight() {
                return curWeight;
            }

            public void setCurWeight(String curWeight) {
                this.curWeight = curWeight;
            }

            public int getMaxCount() {
                return maxCount;
            }

            public void setMaxCount(int maxCount) {
                this.maxCount = maxCount;
            }

            public String getSalesName() {
                return salesName;
            }

            public void setSalesName(String salesName) {
                this.salesName = salesName;
            }
        }
    }
}