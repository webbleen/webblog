package com.webbleen.webblog.service;

import com.webbleen.webblog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    Tag save(Tag tag);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
