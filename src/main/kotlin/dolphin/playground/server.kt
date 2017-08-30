package dolphin.playground

import com.canoo.platform.remoting.server.DolphinAction
import com.canoo.platform.remoting.server.DolphinController
import com.canoo.platform.remoting.server.DolphinModel
import com.canoo.platform.server.spring.DolphinPlatformApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.web.support.SpringBootServletInitializer
import javax.annotation.PostConstruct

fun main(args: Array<String>) {
    SpringApplication.run(FooServerApplication::class.java)
}

@DolphinPlatformApplication
open class FooServerApplication : SpringBootServletInitializer()

@DolphinController(FooConstants.FOO_CONTROLLER_NAME)
open class FooController {
    @DolphinModel
    private lateinit var model: FooPropertyBean

    @PostConstruct
    fun init() {
        println("--- controller init: $model")
        model.valueProperty().onChanged { event ->
            println("--- value changed to '${event.newValue}' (from '${event.oldValue}').")
        }
    }

    @DolphinAction
    fun reset() {
        model.value = null
    }
}