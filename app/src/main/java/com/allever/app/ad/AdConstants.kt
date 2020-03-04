package com.allever.app.ad

import com.allever.lib.ad.ADType
import com.allever.lib.ad.AdBusiness

object AdConstants {

    private val APPID = "ca-app-pub-8815582923430605~2604321696"
    private const val COMMON_BANNER = "ca-app-pub-8815582923430605/1795988120"
    private const val COMMON_INSERT = "ca-app-pub-8815582923430605/1544706681"
    private const val COMMON_VIDEO = "ca-app-pub-8815582923430605/9291334763"
    private const val COMMON_NATIVE = "ca-app-pub-3940256099942544/2247696110"
    val AD_NAME_INSERT = "AD_NAME_INSERT"


    val AD_NAME_BANNER = "AD_NAME_BANNER"


    val AD_NAME_VIDEO = "AD_NAME_VIDEO"

    val AD_NAME_NATIVE = "AD_NAME_NATIVE"

    val AD_NAME_NATIVE_SMALL = "AD_NAME_NATIVE_SMALL"

    val adData = "{\n" +
            "  \"business\": [\n" +
            "    {\n" +
            "      \"name\": \"${AdBusiness.A}\",\n" +
            "      \"appId\": \"$APPID\",\n" +
            "      \"appKey\": \"\",\n" +
            "      \"token\": \"\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"adConfig\": [\n" +
            "    {\n" +
            "      \"name\": \"$AD_NAME_INSERT\",\n" +
            "      \"type\": \"${ADType.INSERT}\",\n" +
            "      \"chain\": [\n" +
            "        {\n" +
            "          \"business\": \"${AdBusiness.A}\",\n" +
            "          \"adPosition\": \"$COMMON_INSERT\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"$AD_NAME_NATIVE\",\n" +
            "      \"type\": \"${ADType.NATIVE}\",\n" +
            "      \"chain\": [\n" +
            "        {\n" +
            "          \"business\": \"${AdBusiness.A}\",\n" +
            "          \"adPosition\": \"$COMMON_NATIVE\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"$AD_NAME_NATIVE_SMALL\",\n" +
            "      \"type\": \"${ADType.NATIVE_SMALL}\",\n" +
            "      \"chain\": [\n" +
            "        {\n" +
            "          \"business\": \"${AdBusiness.A}\",\n" +
            "          \"adPosition\": \"$COMMON_NATIVE\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"$AD_NAME_BANNER\",\n" +
            "      \"type\": \"${ADType.BANNER}\",\n" +
            "      \"chain\": [\n" +
            "        {\n" +
            "          \"business\": \"${AdBusiness.A}\",\n" +
            "          \"adPosition\": \"$COMMON_BANNER\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"$AD_NAME_VIDEO\",\n" +
            "      \"type\": \"${ADType.VIDEO}\",\n" +
            "      \"chain\": [\n" +
            "        {\n" +
            "          \"business\": \"${AdBusiness.A}\",\n" +
            "          \"adPosition\": \"$COMMON_VIDEO\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}\n"
}
