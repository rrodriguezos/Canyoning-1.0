package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import repositories.OrganiserCommentRepository;
import domain.OrganiserComment;

public class StringToOrganiserCommentConverter implements
		Converter<String, OrganiserComment> {
	@Autowired
	OrganiserCommentRepository organiserCommentRepository;

	@Override
	public OrganiserComment convert(String text) {
		OrganiserComment result;
		int id;

		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = organiserCommentRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
