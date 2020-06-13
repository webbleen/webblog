package com.webbleen.webblog.service.impl;

import com.webbleen.webblog.NotFoundException;
import com.webbleen.webblog.dao.BlogRepository;
import com.webbleen.webblog.entity.Blog;
import com.webbleen.webblog.entity.Type;
import com.webbleen.webblog.service.BlogService;
import com.webbleen.webblog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findById(id).get();
        if (b == null) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, b);
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
