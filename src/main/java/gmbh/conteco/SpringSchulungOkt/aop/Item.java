package gmbh.conteco.SpringSchulungOkt.aop;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String name;
    private Long price;
}
