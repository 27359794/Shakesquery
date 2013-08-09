package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.datastructures.list.SinglyLinkedListTest;
import test.retrieval.BooleanModelTest;
import test.retrieval.DocumentTest;
import test.retrieval.InvertedIndexTest;
import test.retrieval.PostingsListTest;

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
