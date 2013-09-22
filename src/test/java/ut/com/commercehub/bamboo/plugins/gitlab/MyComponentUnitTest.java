package ut.com.commercehub.bamboo.plugins.gitlab;

import org.junit.Test;
import com.commercehub.bamboo.plugins.gitlab.MyPluginComponent;
import com.commercehub.bamboo.plugins.gitlab.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}