package okta.springcloudstreams.demo;

import lombok.Data;

@Data
public class AccumulatorMessage {
    int currentValue;
    int total;
    AccumulatorMessage(int currentValue, int total) {
        this.currentValue = currentValue;
        this.total = total;
    }
}