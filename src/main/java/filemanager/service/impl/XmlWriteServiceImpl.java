package filemanager.service.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.Singleton;
import filemanager.model.Feed;
import filemanager.model.Interaction;
import filemanager.service.XmlWriteService;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Singleton
@NoArgsConstructor
public class XmlWriteServiceImpl implements XmlWriteService {

    @Override
    public void writeXmlFile(List<Interaction> interactions, String outputPath) throws IOException {
        File file = new File(generateFileName(LocalDateTime.now(), outputPath));
        file.getParentFile().mkdirs();
        XmlMapper xmlMapper = new XmlMapper();
        Feed feed = new Feed(interactions);
        xmlMapper.writeValue(file, feed);
    }

    @Override
    public String generateFileName(LocalDateTime time, String outputPath) throws FileAlreadyExistsException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        String rootName = outputPath + "/ppe/inbox/bvpixel-";
        String dateCreationName = time.format(formatter);
        String fileName = rootName + dateCreationName + ".xml";
        if (!Files.exists(Paths.get(fileName))) {
            return fileName;
        } else {
            throw new FileAlreadyExistsException("File with name - " + fileName + " already exists!");
        }
    }
}
