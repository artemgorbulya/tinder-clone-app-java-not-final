package ua.com.danit.step.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FreemarkerEngine {
  private final Configuration cfg;

  private FreemarkerEngine(String fullPath) throws IOException {
    this.cfg = new Configuration(Configuration.VERSION_2_3_31) {{
      setDirectoryForTemplateLoading(new File(fullPath));
      setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
      setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
      setLogTemplateExceptions(false);
      setWrapUncheckedExceptions(true);

    }};
  }

  public static FreemarkerEngine directory(final String path_from_project_root) {
    try {
      return new FreemarkerEngine(path_from_project_root);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public void render(String template, Map<String, Object> data, HttpServletResponse resp) {
    resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

    try (PrintWriter w = resp.getWriter()) {
      cfg.getTemplate(template).process(data, w);
    } catch (TemplateException | IOException e) {
      throw new RuntimeException("Freemarker error", e);
    }
  }

}
