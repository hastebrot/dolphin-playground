package dolphin.playground

import com.canoo.platform.server.spring.DolphinPlatformApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.web.support.SpringBootServletInitializer

fun main(args: Array<String>) {
    SpringApplication.run(DolphinTestServer::class.java)
}

@DolphinPlatformApplication
open class DolphinTestServer : SpringBootServletInitializer()
