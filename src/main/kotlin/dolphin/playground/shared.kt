package dolphin.playground

import com.canoo.platform.remoting.ObservableList
import com.canoo.platform.remoting.Property
import com.canoo.platform.remoting.RemotingBean

object FooConstants {
    const val FOO_CONTROLLER_NAME = "FooController"
}

@RemotingBean
class FooPropertyBean {
    private lateinit var stringProperty: Property<String?>
    fun stringProperty() = stringProperty

    var string: String?
        get() = stringProperty.get()
        set(value) = stringProperty.set(value)

    private lateinit var booleanProperty: Property<Boolean?>
    fun booleanProperty() = booleanProperty

    var boolean: Boolean?
        get() = booleanProperty.get()
        set(value) = booleanProperty.set(value)

    private lateinit var array: ObservableList<FooPropertyBean?>
    fun getArray() = array

}
