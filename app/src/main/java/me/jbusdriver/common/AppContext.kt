package me.jbusdriver.common

import android.app.Application
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger
import com.umeng.analytics.MobclickAgent
import jbusdriver.me.jbusdriver.BuildConfig
import me.jbusdriver.http.JAVBusService

/**
 * Created by Administrator on 2017/4/8.
 */
class AppContext : Application() {

    override fun onCreate() {
        super.onCreate()
        instace = this

        Logger.init("old_driver")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .logLevel(if (BuildConfig.DEBUG) LogLevel.FULL else LogLevel.NONE)        // default LogLevel.FULL

        MobclickAgent.setDebugMode(BuildConfig.DEBUG)
    }

    companion object {
        @JvmStatic lateinit var instace: AppContext
        @JvmStatic
        val gson = GsonBuilder().registerTypeAdapter(Int::class.java, JsonDeserializer<Int> { json, _, _ ->
            if (json.isJsonNull || json.asString.isEmpty()) {
                return@JsonDeserializer null
            }
            try {
                return@JsonDeserializer json.asInt
            } catch (e: NumberFormatException) {
                return@JsonDeserializer null
            }
        }).serializeNulls().create()
        val JBusInstances by lazy { arrayMapof<String, JAVBusService>() }
    }
}