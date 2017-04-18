package repository.queries;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class NamedQueryData implements INamedQueryData, Serializable {

	private static final long serialVersionUID = 1L;

	private final String namedQuery;

	private final Map<String, String> parameters;

	public NamedQueryData(String namedQuery, Map<String, String> parameters) {
		this.namedQuery = namedQuery;
		this.parameters = parameters;
	}

	@Override
	public void addParameter(String key, String value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameters(Map<String, String> parameters) {
		parameters.putAll(parameters);
	}

	@Override
	public void removeParameter(String key) {
		parameters.remove(key);
	}

	@Override
	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(parameters);
	}

	@Override
	public String getNamedQuery() {
		return namedQuery;
	}

}
