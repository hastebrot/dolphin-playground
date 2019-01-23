@file:JvmName("Server")
package dolphin.playground

import com.canoo.platform.remoting.server.RemotingAction
import com.canoo.platform.remoting.server.RemotingController
import com.canoo.platform.remoting.server.RemotingModel
import com.canoo.platform.server.spring.DolphinPlatformApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import javax.annotation.PostConstruct

fun main(args: Array<String>) {
    SpringApplication.run(FooServerApplication::class.java)
}

@DolphinPlatformApplication
open class FooServerApplication : SpringBootServletInitializer()

@RemotingController(FooConstants.FOO_CONTROLLER_NAME)
@Suppress("unused")
open class FooController {
    @RemotingModel
    private lateinit var model: FooPropertyBean

    @PostConstruct
    fun init() {
        println("--- controller init: $model")
        model.stringProperty().onChanged { event ->
            println("--- string changed to '${event.newValue}' (from '${event.oldValue}').")
        }
        model.getArray().onChanged { event ->
            println("--- array changed with '${event.changes}.")
        }
    }

    @RemotingAction
    fun reset() {
        model.string = null
    }
}