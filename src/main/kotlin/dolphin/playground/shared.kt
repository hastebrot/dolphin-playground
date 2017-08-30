package dolphin.playground

import com.canoo.platform.remoting.DolphinBean
import com.canoo.platform.remoting.Property

object FooConstants {
    const val FOO_CONTROLLER_NAME = "FooController"
}

@DolphinBean
class FooPropertyBean {
    private lateinit var valueProperty: Property<String?>
    fun valueProperty() = valueProperty

    var value: String?
        get() = valueProperty.get()
        set(value) = valueProperty.set(value)
}
