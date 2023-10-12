package gmbh.conteco.SpringSchulungOkt.batch;

import gmbh.conteco.SpringSchulungOkt.jpa.Student;
import gmbh.conteco.SpringSchulungOkt.jpa.StudentRepository;
import gmbh.conteco.SpringSchulungOkt.rest.RandomNameGenerator;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Stream;

@Configuration
public class JobConfig {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    Job firstJob() {
        Tasklet tasklet = (contribution, chunkContext) -> {
            System.out.println("Hallo");
            return RepeatStatus.FINISHED;
        };

        TaskletStep step = new StepBuilder("simple step", jobRepository)
                .tasklet(tasklet, transactionManager).build();

        return new JobBuilder("firstJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    Job iterableReaderJob(Iterable<String> iterable) {

        Step step = new StepBuilder("Read Iterable And Sout step", jobRepository)
                .<String, String>chunk(3, transactionManager)
                .reader(new IteratorItemReader<>(iterable))
                .processor(s -> s.toUpperCase())
                .writer(chunk -> {
                    System.out.println("new Chunk");
                    chunk.forEach(System.out::println);
                })
                .build();

        return new JobBuilder("Read Iterable Job", jobRepository)
                .start(step).build();
    }

    @Bean
    List<String> getListOfCharacters() {
        return List.of("a", "b", "c", "d", "e", "f", "g", "h");
    }

    @Bean
    @Primary
    Job dataBaseWriteJob(
            ItemReader<Student> reader,
            @Qualifier("ToCsv") ItemWriter<Student> writer) {
        TaskletStep writeStep = new StepBuilder("WriteStep", jobRepository)
                .<Student, Student>chunk(15, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();

        return new JobBuilder("WriteRepoJob", jobRepository)
                .start(writeStep).build();
    }

    @Bean
    @Qualifier("ToCsv")
    ItemWriter<Student> writeStudentToCsv(
            @Value("output/students.csv") String saveLocation,
            @Value("${csv.appendAllowed:true}") boolean appendAllowed
    ) {
        FlatFileItemWriter<Student> writer = new FlatFileItemWriter<>();
        writer.setAppendAllowed(appendAllowed);
        writer.setResource(new FileSystemResource(saveLocation));
        writer.setLineAggregator(new DelimitedLineAggregator<Student>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<>() {{
                setNames(new String[]{"id", "name", "email"});
            }});
        }});
        return writer;
    }

    @Bean
    ItemWriter<Student> writeStudentToDataBase(
            StudentRepository studentRepository
    ) {
        RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
        writer.setRepository(studentRepository);
        return writer;
    }

    @Bean
    ItemReader<Student> randomlyGeneratedStudents(
            RandomNameGenerator randomNameGenerator,
            @Value("100") long amount) {
        Iterable<Student> listOfStudents = Stream.generate(Student::new)
                .limit(amount)
                .map(s -> s.setId(1L))
                .map(s -> s.setName(randomNameGenerator.getName()))
                .map(s -> s.setEmail("random@mail.de"))
                .toList();

        return new IteratorItemReader<>(listOfStudents);
    }


}
