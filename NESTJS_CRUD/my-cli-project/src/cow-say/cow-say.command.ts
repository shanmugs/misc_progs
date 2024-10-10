import { Command, CommandRunner } from 'nest-commander';
import * as cowsay from 'cowsay';
@Command({
  name: 'cowsay',
  options: {
    isDefault: true,
  },
})
export class CowSayCommand extends CommandRunner {
  async run(): Promise<void> {
    console.log(cowsay.say({ text: 'Hello Sathish!' }));
  }
}
