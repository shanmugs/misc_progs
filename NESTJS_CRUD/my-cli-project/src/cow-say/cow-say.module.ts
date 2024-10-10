import { Module } from '@nestjs/common';
import { CowSayCommand } from './cow-say.command';

@Module({
  providers: [CowSayCommand],
})
export class CowSayModule {}
