package com.ups.UPSTrailerTracker.storage

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.nio.file.Files


import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.stream.Stream

@Service
class FileSystemStorageService(@Autowired properties: StorageProperties, private val rootLocation: Path = Paths.get(properties.location)) : StorageService {
    override fun init() {
        Files.createDirectories(rootLocation)
    }

    override fun store(file: MultipartFile) {
        val filename: String = StringUtils.cleanPath(file.originalFilename as String)
        if(file.isEmpty){
            throw StorageException("Failed to store empty file $filename")
        }
        if(filename.contains("..")){
            // This is a security check
            throw StorageException("Cannot store file with relative path outside current directory $filename")
        }
        val inputSteam: InputStream = file.inputStream
        Files.copy(inputSteam, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING)
    }

    override fun loadAll(): Stream<Path> {
        return Files.walk(this.rootLocation, 1)
                .filter{it != this.rootLocation}
                .map({this.rootLocation.relativize(it)})
    }

    override fun load(filename: String): Path = rootLocation.resolve(filename)

    override fun loadAsResource(filename: String): Resource {
        val file: Path = load(filename)
        val resource: Resource = UrlResource(file.toUri())
        if(resource.exists() || resource.isReadable){
            return resource
        } else{
            throw StorageFileNotFoundException("Could not read file: $filename")
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }
}