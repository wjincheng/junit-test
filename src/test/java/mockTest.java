import org.junit.Test;
import org.mockito.InOrder;

import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class mockTest {

    // 验证行为
    @Test
    public void shouldAddOne() {
        List mock = mock(List.class);

        mock.add(1);
        mock.clear();

        verify(mock).add(1);
        verify(mock).clear();
    }

    // 模拟返回结果结果
    @Test
    public void shouldReturnHelloWorld() {
        Iterator iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        assertThat(result, is("hello world world"));
    }

    // 匹配参数
    @Test
    public void with_arguments() {

        Comparable comparable = mock(Comparable.class);
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertThat(comparable.compareTo("Test"), is(1));
        assertThat(comparable.compareTo("Omg"), is(2));
    }

    @Test
    public void with_unspecified_arguments(){
        List list = mock(List.class);
        when(list.get(1)).thenReturn(2);
        when(list.get(anyInt())).thenReturn(1);
        assertThat(list.get(1), is(1));
        assertThat(list.get(99), is(1));
    }

    // 验证确切的调用次数

    @Test
    public void verifying_number_of_invocations() {
        List list = mock(List.class);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(3);

        verify(list).add(1);
        verify(list, times(1)).add(1);
        verify(list, times(2)).add(2);
        verify(list, times(3)).add(3);
        verify(list, never()).add(4);
        verify(list, atLeastOnce()).add(1);
        verify(list, atLeast(2)).add(2);
        verify(list, atMost(3)).add(3);
    }

    // 验证顺序
    @Test
    public void verification_in_order() {
        List list = mock(List.class);
        List list2 = mock(List.class);
        list.add(1);
        list2.add("hello");
        list.add(2);
        list2.add("world");

        //将需要排序的mock对象放入InOrder
        InOrder inOrder = inOrder(list, list2);
        //下面的代码不能颠倒顺序，验证执行顺序
        inOrder.verify(list).add(1);
        inOrder.verify(list2).add("hello");
        inOrder.verify(list).add(2);
        inOrder.verify(list2).add("world");

    }

}
