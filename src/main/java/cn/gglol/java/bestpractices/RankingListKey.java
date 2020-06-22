package cn.gglol.java.bestpractices;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 年级榜单对应RedisKey和榜单名
 * @Author: jingwentao
 * @Date: 2020/6/15
 */
public enum RankingListKey {

    GRADE_1(1L, "primary_school_low", "小学低段"),
    GRADE_2(2L, "primary_school_low", "小学低段"),
    GRADE_3(3L, "primary_school_middle", "小学中段"),
    GRADE_4(4L, "primary_school_middle", "小学中段"),
    GRADE_5(5L, "primary_school_high", "小学高段"),
    GRADE_6(6L, "primary_school_high", "小学高段"),
    GRADE_7(7L, "junior_high_school", "初中段"),
    GRADE_8(8L, "junior_high_school", "初中段"),
    GRADE_9(9L, "junior_high_school", "初中段");

    Long gradeId;
    String key;
    String name;

    RankingListKey(Long gradeId, String key, String name) {
        this.gradeId = gradeId;
        this.key = key;
        this.name = name;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    static final Map<Long, RankingListKey> mappings;

    static {
        Map<Long, RankingListKey> temp = new HashMap<>();
        for (RankingListKey item : values()) {
            temp.put(item.gradeId, item);
        }
        mappings = Collections.unmodifiableMap(temp);
    }

    public static RankingListKey get(Long gradeId) {
        return mappings.get(gradeId);
    }
}
