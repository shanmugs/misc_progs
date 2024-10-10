import { Module } from '@nestjs/common';
import { CowSayModule } from './cow-say/cow-say.module';

@Module({
  imports: [CowSayModule],
})
export class AppModule {}
