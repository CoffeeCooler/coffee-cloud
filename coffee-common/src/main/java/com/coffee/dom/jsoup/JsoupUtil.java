package com.coffee.dom.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
    /**
     * 移除 Document中的某个元素
     * 例如：移除详情中 a 标签
     * @param elementType  元素类型
     * @return
     */
    public static Document removeElement(Document  document,String elementType) {
        Elements as = document.select(elementType);
        for (Element a : as) {
            a.remove();
            document.remove();
        }
        return document;
    }

    /**
     * 移除 Document中的某个元素的某个属性
     * 例如：移除详情中 a 标签的 href 属性
     * @param elementType  元素类型
     * @param attrStr 元素参数属性字符串
     * @return
     */
    public static Document removeElementAttr(Document  document,String elementType, String attrStr) {
        Elements as = document.select(elementType);
        for (Element a : as) {
            a.removeAttr(attrStr);
        }
        return document;
    }

}
