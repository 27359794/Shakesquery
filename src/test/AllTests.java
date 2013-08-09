import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import datastructures.list.SinglyLinkedListTest;
import retrieval.BooleanModelTest;
import retrieval.DocumentTest;
import retrieval.InvertedIndexTest;
import retrieval.PostingsListTest;

@RunWith(Suite.class)
@SuiteClasses({
	BooleanModelTest.class,
	DocumentTest.class,
	InvertedIndexTest.class,
	PostingsListTest.class,
	SinglyLinkedListTest.class
})
public class AllTests {

}
