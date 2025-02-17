package com.aier.cloud.biz.service.biz.amcs.idr.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * 7.3	民族代码
 *
 * @author Luorz
 * @since 2023年5月09日 下午3:09:04
 */
public enum NationEnum implements EnumDict<NationEnum> {
	K01("01","汉族"),
	K02("02","蒙古族"),
	K03("03","回族"),
	K04("04","藏族"),
	K05("05","维吾尔族"),
	K06("06","苗族"),
	K07("07","彝族"),
	K08("08","壮族"),
	K09("09","布依族"),
	K10("10","朝鲜族"),
	K11("11","满族"),
	K12("12","侗族"),
	K13("13","瑶族"),
	K14("14","白族"),
	K15("15","土家族"),
	K16("16","哈尼族"),
	K17("17","哈萨克族"),
	K18("18","傣族"),
	K19("19","黎族"),
	K20("20","傈僳族"),
	K21("21","佤族"),
	K22("22","畲族"),
	K23("23","高山族"),
	K24("24","拉祜族"),
	K25("25","水族"),
	K26("26","东乡族"),
	K27("27","纳西族"),
	K28("28","景颇族"),
	K29("29","柯尔克孜族"),
	K30("30","土族"),
	K31("31","达斡尔族"),
	K32("32","仫佬族"),
	K33("33","羌族"),
	K34("34","布朗族"),
	K35("35","撒拉族"),
	K36("36","毛南族"),
	K37("37","仡佬族"),
	K38("38","锡伯族"),
	K39("39","阿昌族"),
	K40("40","普米族"),
	K41("41","塔吉克族"),
	K42("42","怒族"),
	K43("43","乌孜别克族"),
	K44("44","俄罗斯族"),
	K45("45","鄂温克族"),
	K46("46","德昂族"),
	K47("47","保安族"),
	K48("48","裕固族"),
	K49("49","京族"),
	K50("50","塔塔尔族"),
	K51("51","独龙族"),
	K52("52","鄂伦春族"),
	K53("53","赫哲族"),
	K54("54","门巴族"),
	K55("55","珞巴族"),
	K56("56","基诺族"),
	K57("57","外国血统中国籍人士"),
	K97("97","其他"),
	K98("98","不详")  ;

    private String code;
    private String value;

    private NationEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getEnumCode() {
        return code;
    }

    @Override
    public String getEnumDesc() {
        return value;
    }



    public static boolean isInclude(String key){
        boolean include = false;
        for (NationEnum e: NationEnum.values()){
            if(e.name().equals(key)){
                include = true;
                break;
            }
        }
        return include;
    }
}
