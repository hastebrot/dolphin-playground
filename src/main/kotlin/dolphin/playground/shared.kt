package dolphin.playground

import com.canoo.platform.remoting.DolphinBean
import com.canoo.platform.remoting.Property

@DolphinBean
class PropertyTestBean {
    lateinit var stringValue: Property<String>
}
