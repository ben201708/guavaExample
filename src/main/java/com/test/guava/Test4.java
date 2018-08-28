package com.test.guava;

import com.google.common.collect.*;

import java.util.*;

public class Test4 {
    /**
     * 内部标签列表
     */
    private static final List<Integer> innerTags = Lists.newArrayList(124, 214);
    /**
     * 微信公众号用户标签(111未绑定/110已绑定未授信/109已授信未成交/108已授信已成交)
     */
    private static final Map<String, Integer> mapTags = ImmutableMap.of(
            "NOT_BIND", 111,             //未绑定
            "124_!已授信", 110,          //已绑定未授信
            "124_已授信&214_否", 109,    //已授信未成交
            "124_已授信&214_是", 108     //已授信已成交
    );
    private static final String avaitorStr = "tagId+'_'+tagDesc";

    private List<String> listQuta = Lists.newArrayList("已授信", "预授信", "有发起授信-风控拒绝");
    private List<String> listCons = Lists.newArrayList("是", "否");

    public static void main(String[] args) {

        UserBean i1 = new UserBean();
        UserBean i3 = new UserBean();
        UserBean i2 = new UserBean();
        UserBean i4 = new UserBean();
        UserBean i5 = new UserBean();
        UserBean i6 = new UserBean();
        UserBean i7 = new UserBean();
        UserBean i8 = new UserBean();
        i1.setOpenId("aaa1");
        i1.setUid(1);
        i2.setOpenId("aaa2");
        i2.setUid(2);
        i3.setOpenId("aaa3");
        i3.setUid(3);
        i4.setOpenId("aaa4");
        i4.setUid(4);
        i5.setOpenId("aaa5");
        i5.setUid(5);
        i6.setOpenId("aaa6");
        i6.setUid(6);
        i7.setOpenId("aaa7");
        i7.setUid(7);
        i8.setOpenId("aaa8");
        i8.setUid(8);

        Set<UserBean> userBeans = Sets.newHashSet(i1, i2, i3, i4, i5, i6, i7, i8);

        Set<UserBean> wxUserBeans = new Test4().getInfo(userBeans);
        System.out.println(userBeans.toString());
        System.out.println(wxUserBeans.toString());

        new Test4().setUserTag(userBeans);

    }

    private Set<UserBean> getInfo(Set<UserBean> userBeans) {

        //遍历获取用户分期乐标签
        for (UserBean userBean : userBeans) {

            Map<Integer, InnerTag> tagMap = new HashMap<>();
            String tagKeyStr = "";

            for (Integer innerTagId : innerTags) {

                Random random = new Random();

                String labelDesc = "";
                if ("" != tagKeyStr) {
                    int n = random.nextInt(listCons.size());
                    labelDesc = listCons.get(n);
                } else {
                    int n = random.nextInt(listQuta.size());
                    labelDesc = listQuta.get(n);
                }

                //获取用户击中标签的内容
                InnerTag innerTagTmp = new InnerTag();
                innerTagTmp.setTagId(innerTagId);
                innerTagTmp.setTagDesc(labelDesc);
                tagMap.put(innerTagId, innerTagTmp);

                if (innerTagId.equals(124) && !"已授信".equals(labelDesc)) {
                    labelDesc = "!已授信";
                    tagKeyStr = tagKeyStr + innerTagId + "_" + labelDesc + "&";
                    break;
                }

                tagKeyStr = tagKeyStr + innerTagId + "_" + labelDesc + "&";
            }
            String tagKey = tagKeyStr.substring(0, tagKeyStr.length() - 1);
            userBean.setWechatTagId(mapTags.get(tagKey));
            userBean.setTagInfo(tagMap);
            userBean.setWechatTagStr(tagKey);
        }
        return userBeans;
    }

    /**
     * 批量更新微信用户标签
     *
     * @param userBeans
     * @return
     */
    private void setUserTag(Set<UserBean> userBeans) {

        //遍历获取用户分期乐标签
        Multimap<Integer, String> multimap = ArrayListMultimap.create();
        for (UserBean userBean : userBeans) {
            multimap.put(userBean.getWechatTagId(), userBean.getOpenId());
        }

        System.out.println("打印结果：" + multimap.toString());
        //批量取消微信公众号其他标签

        //批量打上用户标签

        return;
    }

    static class UserBean {
        //分期乐uid
        private Integer uid;
        //微信公众号openId
        private String openId;
        //用户分期乐标签信息
        private Map<Integer, InnerTag> tagInfo;
        //微信用户标签ID
        private Integer wechatTagId;
        private String wechatTagStr;

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public Map<Integer, InnerTag> getTagInfo() {
            return tagInfo;
        }

        public void setTagInfo(Map<Integer, InnerTag> tagInfo) {
            this.tagInfo = tagInfo;
        }

        public Integer getWechatTagId() {
            return wechatTagId;
        }

        public void setWechatTagId(Integer wechatTagId) {
            this.wechatTagId = wechatTagId;
        }


        public String getWechatTagStr() {
            return wechatTagStr;
        }

        public void setWechatTagStr(String wechatTagStr) {
            this.wechatTagStr = wechatTagStr;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "uid=" + uid +
                    ", openId='" + openId + '\'' +
                    ", tagInfo=" + tagInfo +
                    ", wechatTagId=" + wechatTagId +
                    ", wechatTagStr='" + wechatTagStr + '\'' +
                    '}';
        }
    }

    static class InnerTag {
        private Integer tagId;
        private String tagDesc;

        public Integer getTagId() {
            return tagId;
        }

        public void setTagId(Integer tagId) {
            this.tagId = tagId;
        }

        public String getTagDesc() {
            return tagDesc;
        }

        public void setTagDesc(String tagDesc) {
            this.tagDesc = tagDesc;
        }

        @Override
        public String toString() {
            return "InnerTag{" +
                    "tagId=" + tagId +
                    ", tagDesc='" + tagDesc + '\'' +
                    '}';
        }
    }
}
