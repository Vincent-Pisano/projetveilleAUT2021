package com.veille2021.reactiveprogbackend.converter;


import com.veille2021.reactiveprogbackend.model.Comment;
import io.r2dbc.spi.Row;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class CommentConverter implements Converter<Row, Comment> {

    public Comment convert(Row source) {
        Comment c = new Comment();
        c.setIdComment(source.get("id_comment", Integer.class));
        c.setComment(source.get("comment", String.class));
        return c;
    }
}
