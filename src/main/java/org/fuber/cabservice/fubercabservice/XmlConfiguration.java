package org.fuber.cabservice.fubercabservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:cab-repo.xml"})
public class XmlConfiguration {

}
