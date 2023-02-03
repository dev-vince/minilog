package dev.vince.log.util;

import dev.vince.log.hook.Hook;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public final class ClassUtil {
    public static ArrayList<Class<?>> getClassesRunning() {
        final ArrayList<Class<?>> classes = new ArrayList<>();
        try {
            final Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("");

            while (resources.hasMoreElements()) {
                final URL resource = resources.nextElement();
                final File directory = new File(resource.getFile());
                if (directory.exists()) {
                    verifyClass(directory, classes, directory);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static void verifyClass(final File file, final ArrayList<Class<?>> classes, final File root) {
        try {
            if (file.isDirectory()) {
                for (File f : Objects.requireNonNull(file.listFiles())) {
                    verifyClass(f, classes, root);
                }
            } else if (file.isFile() && file.getName().endsWith(".class")) {
                final String className = file.getPath().replace(".class", "").replace(root.getPath() + "\\","").replace("/", ".").replace("\\", ".");
                final Class<?> clazz = Class.forName(className);

                classes.add(clazz);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
