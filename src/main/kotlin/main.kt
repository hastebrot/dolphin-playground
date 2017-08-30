import com.canoo.platform.core.DolphinRuntimeException
import com.canoo.platform.remoting.DolphinBean
import com.canoo.platform.remoting.Property
import com.canoo.platform.remoting.client.ClientContext
import com.canoo.platform.remoting.client.ClientInitializationException
import com.canoo.platform.remoting.client.ClientShutdownException
import com.canoo.platform.remoting.client.javafx.DolphinPlatformApplication
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.control.Label
import java.net.URL

fun main(args: Array<String>) {
    Application.launch(DolphinTestApplication::class.java)
}

@DolphinBean
class PropertyTestBean {
    lateinit var stringValue: Property<String>
}

class DolphinTestApplication : DolphinPlatformApplication() {
    override fun getServerEndpoint() = URL("http://localhost:9999/dolphin")

    init {
        println("init")
    }

    override fun start(stage: Stage,
                       clientContext: ClientContext) {
        println("start")
        stage.scene = Scene(Label("Hello Dolphin"))
        stage.show()
    }

    override fun onInitializationError(stage: Stage,
                                       exception: ClientInitializationException,
                                       possibleCauses: MutableIterable<DolphinRuntimeException>) {
        println("init error: " + exception)
        Platform.exit()
    }

    override fun onShutdownError(exception: ClientShutdownException) {
        println("shutdown error: " + exception)
        Platform.exit()
    }
}
