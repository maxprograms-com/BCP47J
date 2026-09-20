/*******************************************************************************
 * Copyright (c) 2022-2026 Maxprograms. All rights reserved.
 *
 * This software is the proprietary property of Maxprograms.
 * Use, modification, and distribution are subject to the terms of the 
 * Software License Agreement found in the root of this distribution 
 *
 * Unauthorized redistribution or commercial use is strictly prohibited.
 *******************************************************************************/
package com.maxprograms.languages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RegistryEntry {

	private Map<String, String> table;

	public RegistryEntry(String entry) {
		table = new HashMap<>();
		parseEntry(entry);
	}

	private void parseEntry(String entry) {
		String[] lines = entry.split("\n");
		String lastType = null;
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.startsWith(" ") || line.startsWith("\t")) {
				if (lastType != null) {
					table.put(lastType, table.get(lastType) + " " + line.trim());
				}
				continue;
			}
			int separator = line.indexOf(':');
			if (separator == -1) {
				continue;
			}
			String type = line.substring(0, separator).trim();
			String value = line.substring(separator + 1).trim();
			lastType = type;
			if (!table.containsKey(type)) {
				table.put(type, value);
			} else {
				table.put(type, table.get(type) + " | " + value);
			}
		}
	}

	public Set<String> getTypes() {
		return table.keySet();
	}

	public String get(String string) {
		return table.get(string);
	}

	public String getType() {
		if (table.containsKey("Type")) {
			return table.get("Type");
		}
		return null;
	}

	public String getDescription() {
		if (table.containsKey("Description")) {
			return table.get("Description");
		}
		return null;
	}

	public String getSubtag() {
		if (table.containsKey("Subtag")) {
			return table.get("Subtag");
		}
		return null;
	}
}
