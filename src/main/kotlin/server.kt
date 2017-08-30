import com.canoo.platform.server.spring.DolphinPlatformApplication
import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(DolphinTestServer::class.java)
}

@DolphinPlatformApplication
class DolphinTestServer

