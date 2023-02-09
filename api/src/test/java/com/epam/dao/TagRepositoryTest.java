package com.epam.dao;

import com.epam.domain.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TagRepositoryTest {
    private static final String TAG_NAME_1 = "test";
    private static final String TAG_NAME_2 = "tests";

    private Tag tag1;
    private Tag tag2;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        tag1 = new Tag();
        tag1.setName(TAG_NAME_1);

        tag2 = new Tag();
        tag2.setName(TAG_NAME_2);
    }

    @Test
    public void testGetTagById() {
        Tag expected = entityManager.persist(tag1);

        Tag actual = tagRepository.findById(expected.getId()).get();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTagById_Empty() {
        Optional<Tag> expected = Optional.empty();

        Optional<Tag> actual = tagRepository.findById(1L);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTagByName() {
        Tag expected = entityManager.persist(tag1);

        Tag actual = tagRepository.findTagByNameIgnoreCase(expected.getName()).get();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTagByName_Empty() {
        Optional<Tag> expected = Optional.empty();

        Optional<Tag> actual = tagRepository.findTagByNameIgnoreCase(TAG_NAME_1);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTags_allResults() {
        List<Tag> expected = List.of(entityManager.persist(tag1), entityManager.persist(tag2));

        List<Tag> actual = tagRepository.findAll(PageRequest.of(0, 2));

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTags_oneResult() {
        entityManager.persist(tag1);
        List<Tag> expected = List.of(entityManager.persist(tag2));

        List<Tag> actual = tagRepository.findAll(PageRequest.of(1, 1));

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTagsByPartName_twoResults() {
        List<Tag> expected = List.of(entityManager.persist(tag1), entityManager.persist(tag2));

        List<Tag> actual = tagRepository.findTagsByNameContainsIgnoreCase(TAG_NAME_1, PageRequest.of(0, 2));

        assertEquals(expected, actual);
    }

    @Test
    public void testGetTagsByPartName_oneResult() {
        entityManager.persist(tag1);
        List<Tag> expected = List.of(entityManager.persist(tag2));

        List<Tag> actual = tagRepository.findTagsByNameContainsIgnoreCase(TAG_NAME_2, PageRequest.of(0, 2));

        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteTags() {
        Tag savedTag = entityManager.persist(tag1);

        tagRepository.deleteById(savedTag.getId());

        Tag actual = tagRepository.findById(savedTag.getId()).orElse(null);

        assertNull(actual);
    }

    @Test
    public void testCreateTag() {
        Tag actual = tagRepository.save(tag1);

        assertEquals(actual.getId(), 1L);
        assertEquals(actual.getName(), TAG_NAME_1);
    }
}