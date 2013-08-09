package query;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import query.datastructures.list.SinglyLinkedListTest;
import query.retrieval.BooleanModelTest;
import query.retrieval.DocumentTest;
import query.retrieval.InvertedIndexTest;
import query.retrieval.PostingsListTest;
import query.utils.FileReadingTest;

@RunWith(Suite.class)
@SuiteClasses({
	BooleanModelTest.class,
	DocumentTest.class,
	InvertedIndexTest.class,
	PostingsListTest.class,
	SinglyLinkedListTest.class,
	FileReadingTest.class
})
public class AllTests {

}
