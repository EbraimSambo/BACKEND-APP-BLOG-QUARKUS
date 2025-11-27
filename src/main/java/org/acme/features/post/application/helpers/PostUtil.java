package org.acme.features.post.application.helpers;

public class PostUtil {

    public static String generateSlug(String title) {
        String slug = title.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
        return slug;
    }

    public static String generateUniqueSlug(String baseSlug, int attempt) {
        if (attempt == 0)
            return baseSlug;
        return baseSlug + "-" + attempt;
    }
}
