package com.webbleen.webblog.service.impl;

import com.webbleen.webblog.NotFoundException;
import com.webbleen.webblog.dao.BlogRepository;
import com.webbleen.webblog.entity.Blog;
import com.webbleen.webblog.entity.Type;
import com.webbleen.webblog.service.BlogService;
import com.webbleen.webblog.utils.MarkdownUtils;
import com.webbleen.webblog.utils.MyBeanUtils;
import com.webbleen.webblog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-13 11:03
 * @description：
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.findById(id).get();
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogRepository.updateViews(id); //blog.views+1

        return b;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery query) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (query.getTitle() != null && !"".equals(query.getTitle())) {
                    predicates.add(cb.like(root.get("title"), "%"+query.getTitle()+"%"));
                }
                if (query.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), query.getTypeId()));
                }
                if (query.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), query.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, String query) {
        return blogRepository.findByQuery(pageable, query);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Long tagId) {
        // 关联查询
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"), tagId);
            }
        }, pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "updateTime"));//TODO:only recommend
        return blogRepository.findTop(pageable);
    }

    @Override
    public LinkedHashMap<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYeas();
        LinkedHashMap<String, List<Blog>> archiveMap = new LinkedHashMap<>();
        for (String year : years) {
            archiveMap.put(year, blogRepository.findByYear(year));
        }
        return archiveMap;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findById(id).get();
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
