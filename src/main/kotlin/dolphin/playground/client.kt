@file:JvmName("Client")
package dolphin.playground

import com.canoo.platform.client.PlatformClient
import com.canoo.platform.core.DolphinRuntimeException
import com.canoo.platform.remoting.client.ClientContext
import com.canoo.platform.remoting.client.ClientContextFactory
import com.canoo.platform.remoting.client.ClientInitializationException
import com.canoo.platform.remoting.client.javafx.DolphinPlatformApplication
import com.canoo.platform.remoting.client.javafx.FXBinder
import com.canoo.platform.remoting.client.javafx.view.AbstractViewController
import com.sun.javafx.application.PlatformImpl
import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import java.net.URI
import java.net.URL
import java.util.concurrent.CountDownLatch

fun main(args: Array<String>) {
    val barrier = CountDownLatch(1)
    PlatformImpl.startup { barrier.countDown() }
    barrier.await()

    PlatformImpl.runLater {
        val endpoint = URI("http://localhost:8090/dolphin")

        val clientContext = PlatformClient.getService(ClientContextFactory::class.java)
            .create(PlatformClient.getClientConfiguration(), endpoint)

        clientContext.connect().get()

        val fooNode = FooViewBinder(clientContext).rootNode

        val stage = Stage()
        stage.scene = Scene(StackPane(fooNode).apply { padding = Insets(10.0) }, 200.0, 100.0)
        stage.show()
    }

//    Application.launch(FooClientApplication::class.java)
}

class FooViewBinder(clientContext: ClientContext) :
        AbstractViewController<FooPropertyBean>(clientContext, FooConstants.FOO_CONTROLLER_NAME) {
    private val textField by lazy { TextField("empty") }

    override fun init() {
        try {
            println("--- binder init: $textField")
            textField.textProperty().addListener { _, oldValue, newValue ->
                println("--- string changed to '$newValue' (from '$oldValue').")
            }
            FXBinder.bind(textField.textProperty()).bidirectionalTo(model.stringProperty())

        }
        catch (exception: Throwable) {
            println("--- binder init exception")
            exception.printStackTrace()
        }
    }

    override fun getRootNode() = textField
}

class FooClientApplication : DolphinPlatformApplication() {
    override fun getServerEndpoint() = URL("http://localhost:8090/dolphin")

    init {
        println("init")
    }

    override fun start(stage: Stage,
                       clientContext: ClientContext) {
        println("start")
        stage.scene = Scene(StackPane(Label("Hello Dolphin")), 200.0, 200.0)
        stage.show()
    }

    override fun onInitializationError(stage: Stage,
                                       exception: ClientInitializationException,
                                       possibleCauses: MutableIterable<DolphinRuntimeException>) {
        println("init error: $exception")
        Platform.exit()
    }

    override fun onRuntimeError(primaryStage: Stage?, exception: DolphinRuntimeException) {
        println("runtime error: $exception")
        Platform.exit()
    }
}
