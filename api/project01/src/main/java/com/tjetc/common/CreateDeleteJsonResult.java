package com.tjetc.common;

/**
 * 创建删除操作的JsonResult工具类，根据操作成功与否创建相应的JsonResult对象。
 */
public class CreateDeleteJsonResult {
    /**
     * 根据操作结果创建相应的JsonResult对象。
     *
     * @param flag 操作是否成功的标志
     * @return JsonResult对象，表示操作结果
     */
    public static JsonResult createDeleteJsonResult(boolean flag){
        if (flag) {
            return new JsonResult<>(0, "", null);
        } else {
            return new JsonResult<>(1, "删除失败", null);
        }
    }



}
